#!/bin/bash

# --device /dev/ttyUSB0 --device /dev/ttyUSB1
docker run -p 8888:22 -p 8765:8765 \
	--cap-add=SYS_PTRACE --security-opt seccomp=unconfined \
	-v "$HOME":"$HOME" \
	--device /dev/video0 --device /dev/video1 \
	--network=host \
	-e DISPLAY="$DISPLAY" -v /tmp/.X11-unix:/tmp/.X11-unix \
	--name="ros-humble" -it 6275fe5bc093 bash
# x转发-非必要1
# xhost +

#--device /dev/video2 --device /dev/video3 \
