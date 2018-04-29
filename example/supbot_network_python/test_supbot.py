import socket
import json


def main():
    serversocket = socket.socket()
    host = "localhost"
    port = 23456
    serversocket.bind((host, port))
    serversocket.listen(1)

    while True:
        s, addr = serversocket.accept()
        msg = s.recv(1024).decode("utf-8")#[1:]
        print(msg)
        msgJson = json.loads(msg)

        response = processResponse(msgJson["request"])

        sendJson = {"id": msgJson["id"], "response": response}
        response = json.dumps(sendJson)
        print(response)
        sendResponse(response)
        s.close()


def sendResponse(msg):
    s = socket.socket()
    host = "localhost"
    port = 12345
    s.connect((host, port))
    s.send(msg.encode('utf-8'))
    s.close()


def processResponse(requestString):
    return requestString

if __name__ == '__main__':
    main()
