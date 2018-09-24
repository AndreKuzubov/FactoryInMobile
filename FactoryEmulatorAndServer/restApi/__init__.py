from flask import Flask
import json
from flask import request
import os
from factory import oilPumpingEmulation

app = Flask(__name__, static_folder='view/static', static_url_path='/static')


@app.route("/auth", methods=['POST', 'GET'])
def auth():
    login = request.args['login']
    passw = request.args['passw']

    response = json.dumps({
        'name': login,
        'pass': passw,
        "login": login
    }, ensure_ascii=False)
    print("auth:", response)
    return response


@app.route("/factory<name>", methods=['POST', 'GET'])
def factoryDetails(name='', methods=['POST', 'GET']):
    response = json.dumps({
        'factory': name,
        'pumpes': [p.toDict() for p in oilPumpingEmulation.pumpes],
        'tankers': [t.toDict() for t in oilPumpingEmulation.tankers]},
        ensure_ascii=False,
        indent=2)

    return response


@app.route("/")
@app.route("/<page>")
def index(page="index"):
    print(page)
    return "{}"


def run():
    print('server start ' + str(os.getpid()))
    app.run(host='0.0.0.0', port=5050, debug=True)
