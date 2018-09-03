# spring-boot 2.0

Simple example to show watching the custom resource definition PipelineActivities

The example writes any PipelineActivites to the console. Use `jx logs --env staging jx-staging-<app name>` to view the log. Then trigger a promotion using `jx promote <app name> --version=<version in staging> --env production`.
