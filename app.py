from flask import Flask, jsonify
from nacos import NacosClient
import os
import signal
import sys

app = Flask(__name__)

# Nacos 配置
nacos_config = {
    "server_addresses": "localhost:8848",  # Nacos 服务器地址
    "namespace": "public",  # 命名空间，默认是 public
    "username": "nacos",  # 用户名
    "password": "nacos",  # 密码
}

# 创建 Nacos 客户端
nacos_client = NacosClient(
    server_addresses=nacos_config["server_addresses"],
    namespace=nacos_config["namespace"],
    username=nacos_config["username"],
    password=nacos_config["password"]
)

# 服务注册信息
service_info = {
    "service_name": "express-service",  # 服务名称
    "ip": "127.0.0.1",  # 服务 IP
    "port": 5000,  # 服务端口
    "weight": 1.0,  # 权重
    "enable": True,  # 是否启用
    "healthy": True,  # 是否健康
    "metadata": {"protocol": "http"},  # 元数据
    "group_name": "DEFAULT_GROUP"  # 分组名称
}

@app.route('/hello', methods=['GET'])
def hello():
    return "py客户端服务正常，服务器问好：Hello! shy"

def register_service():
    try:
        nacos_client.add_naming_instance(
            service_name=service_info["service_name"],
            ip=service_info["ip"],
            port=service_info["port"],
            weight=service_info["weight"],
            enable=service_info["enable"],
            healthy=service_info["healthy"],
            metadata=service_info["metadata"],
            group_name=service_info["group_name"]
        )
        print(f"Service registered to Nacos: {service_info}")
    except Exception as e:
        print(f"Failed to register service to Nacos: {e}")
        sys.exit(1)

def deregister_service():
    try:
        nacos_client.remove_naming_instance(
            service_name=service_info["service_name"],
            ip=service_info["ip"],
            port=service_info["port"],
            group_name=service_info["group_name"]
        )
        print(f"Service deregistered from Nacos: {service_info}")
    except Exception as e:
        print(f"Failed to deregister service from Nacos: {e}")

def signal_handler(sig, frame):
    print("Received SIGINT, deregistering service and exiting...")
    deregister_service()
    sys.exit(0)

if __name__ == '__main__':
    # 注册服务到 Nacos
    register_service()

    # 设置信号处理程序
    signal.signal(signal.SIGINT, signal_handler)

    # 启动 Flask 应用
    app.run(host='0.0.0.0', port=service_info["port"])