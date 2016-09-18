#!/bin/sh

rm -rf tmp && mkdir tmp && (cd tmp && jar xvf ../api.war > structure.log) && exec java -jar api.war > run.log &
