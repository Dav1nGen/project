import torch
import onnx
import torch.nn

# 加载预训练的 PyTorch 模型
# model = torch.load("./yolov5s.pt")
model = torch.load(
    "./yolov5s.pt", map_location=torch.device("cpu")
)  # 加载模型字典对象并将模型移动到 CPU

# 从字典中提取模型部分
if isinstance(model, dict):
    model = model["model"]

model.eval()

# 创建示例输入张量
dummy_input = torch.randn(1, 3, 640, 640, dtype=torch.float16).to(torch.device("cpu"))

# 指定输出文件路径
onnx_file_path = "./yolo.onnx"

# 导出模型为 ONNX 格式
torch.onnx.export(model, dummy_input, onnx_file_path, verbose=True, opset_version=11)
