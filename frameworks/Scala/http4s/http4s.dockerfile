# FROM openjdk:15 AS builder
# WORKDIR /http4s
# COPY project project
# COPY src src
# COPY build.sbt build.sbt
# COPY sbt sbt
# RUN ./sbt assembly -batch && \
#     mv target/scala-2.13/http4s-assembly-1.0.jar . && \
#     rm -Rf target && \
#     rm -Rf project/target && \
#     rm -Rf ~/.sbt && \
#     rm -Rf ~/.ivy2 && \
#     rm -Rf /var/cache
FROM openjdk:15
WORKDIR /http4s
# COPY --from=builder /http4s/http4s-assembly-1.0.jar /http4s/http4s-assembly-1.0.jar
COPY target/scala-2.13/http4s-assembly-1.0.jar /http4s/http4s-assembly-1.0.jar
COPY async-profiler-2.0-linux-x64 /async-profiler

EXPOSE 8080

CMD java \
      -server \
      -Xms2g \
      -Xmx2g \
      -XX:+AlwaysPreTouch \
      -XX:ParallelGCThreads=12 \
      -Xlog:gc=debug:file=/tmp/gc.log:time,uptime,level,tags:filecount=5,filesize=100m \
      -Dcats.effect.stackTracingMode=disabled \
      -jar \
      http4s-assembly-1.0.jar \
      tfb-database
