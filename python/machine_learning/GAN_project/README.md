# 第三次作业

## 项目文件树

```shell
.
├── dataset
│   └── MNIST                           #数据集
├── dist                                #ppt（html）
│   ├── index.html
├── front
│   ├── back
│   │   ├── gan.py
│   │   ├── **init**.py
│   │   ├── main.py
│   │   └── train.py
│   ├── generator_form.py
│   ├── **init**.py
│   ├── signals.py
│   ├── threads.py
│   └── train_form.py
├── main_gui.py                         #GUI
├── main.py                             #CLI
├── models
│   ├── discriminator_output(echops==1).pth
│   ├── Figure_1(echops==1).png
│   └── generator_output(echops==1).pth
├── picture
│   ├── fake
│   │   └── \*.png
│   └── true
│       └── \*.png
├── README.md
├── requirements_cli.txt
└── requirements_gui.txt
```

## 运行项目

> Gui版本有待调整

```shell
pip install  -r requirements_cli.txt
python3 main.py
```
