import gxipy as gx
import cv2
import numpy as np
#revise
# 枚举设备。dev_info_list 是设备信息列表，列表的元素个数为枚举到的设备个数，列表元素是字典，其中包含设备索引（index）、ip 信息（ip）等设备信息
device_manager = gx.DeviceManager()
dev_num,dev_info_list =device_manager.update_device_list()
if dev_num ==0:
    print("error: could not find gx camera!")

# 打开设备 获取设备基本信息列表
strSN=dev_info_list[0].get("sn")
print(strSN)
#通过序列号打开设备
cam=device_manager.open_device_by_sn(strSN)
cam.ExposureTime.set(10000)
cam.BalanceWhiteAuto.set(enum_value=2)

while(1):
    # 开始采集
    #cam.BalanceWhiteAuto.set(gx.GxAutoEntry.ONCE)
    cap=cam.stream_on()
    raw_image = cam.data_stream[0].get_image()
    rgb_image = raw_image.convert("RGB")
    #frame_count=cam.data_stream[0].StreamDeliveredFrameCount.get()
    fps=cam.CurrentAcquisitionFrameRate.get()
    result_array=rgb_image.get_numpy_array()
    print(result_array,fps)
    
        

    

# cap = cv2.VideoCapture(0)
# imgs = cap.read()  # guarantee first frame
# print(imgs)
    