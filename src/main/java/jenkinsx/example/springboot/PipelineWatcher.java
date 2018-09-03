package jenkinsx.example.springboot;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.jenkins.x.client.PipelineClient;
import io.jenkins.x.client.kube.PipelineActivity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class PipelineWatcher {

    private final SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private final Logger log = Logger.getLogger(PipelineWatcher.class.getName());

    private final PipelineClient pipelineClient;
    private Date creationTimestamp;

    public PipelineWatcher() {
        KubernetesClient kubernetesClient = new DefaultKubernetesClient();
        this.pipelineClient = PipelineClient.newInstance(kubernetesClient, "jx");
    }

    private void watch() {
        Watcher<PipelineActivity> listener = new Watcher<PipelineActivity>() {
            @Override
            public void eventReceived(Action action, PipelineActivity pipelineActivity) {
                try {
                    Date ct = timestampFormat.parse(pipelineActivity.getMetadata().getCreationTimestamp());
                    PipelineWatcher.this.creationTimestamp = PipelineWatcher.this.creationTimestamp == null ? ct : creationTimestamp;
                    if (ct.getTime() > creationTimestamp.getTime()) {
                        PipelineWatcher.this.creationTimestamp = ct;
                        log.log(Level.INFO, "New Event at " + ct.toString());
                        log.log(Level.INFO, pipelineActivity.toString());
                    }
                } catch (ParseException e) {
                    log.log(Level.SEVERE, "Failed to parse creationTimestamp " + pipelineActivity.getMetadata().getCreationTimestamp(), e);
                }
            }

            @Override
            public void onClose(KubernetesClientException e) {
                PipelineWatcher.this.log.log(Level.INFO, "Reconnect stream");
                PipelineWatcher.this.watch();
            }
        };
        this.pipelineClient.addListener(listener);
    }

    public void start() {
        this.watch();
        this.pipelineClient.start();
    }

}