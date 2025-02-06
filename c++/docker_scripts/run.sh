#!/bin/bash

# --device /dev/ttyUSB0 --device /dev/ttyUSB1
docker run -p 8889:22 -p 8766:8765 \
	--cap-add=SYS_PTRACE --security-opt seccomp=unconfined \
	-v "$HOME":"$HOME" \
	--device /dev/video0 --device /dev/video1 \
	--network=host \
	-e DISPLAY="$DISPLAY" -v /tmp/.X11-unix:/tmp/.X11-unix \
	--name="Rizon4" -it 6275fe5bc093 bash
# x转发
xhost +

#--device /dev/video2 --device /dev/video3 \
