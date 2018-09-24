import os

from model.pump import *
from model.tank import *
import time
import threading
import queue

pumpes = []
tankers = []


def run():
    t = threading.Thread(target=runAsync)
    t.start()


def init():
    global pumpes, tankers
    pumpes.clear()
    tankers.clear()

    tankerShipDiesel = Tank(tag='tankerShipDiesel', volume=1000)
    portTankDiesel1 = Tank(tag='portTankDiesel1', volume=200)
    portTankDiesel1_1 = Tank(tag='portTankDiesel1_1', volume=2000)
    portTankDiesel1.fill = 40
    portTankDiesel1_1.fill = 1500

    portTankDiesel1ToTankerShipDiesel = Pump(tag="portTankDiesel1-tankerShipDiesel",
                                             allowTurnovers=7,
                                             allowVoltage=1000,
                                             tankSource=portTankDiesel1,
                                             tankDirection=tankerShipDiesel)
    portTankDiesel1ToTankerShipDiesel.turnOn(voltage=250, turnovers=5)

    portTankDiesel1_1ToportTankDiesel1 = Pump(tag="portTankDiesel1_1-portTankDiesel1",
                                              allowTurnovers=12,
                                              allowVoltage=300,
                                              tankSource=portTankDiesel1_1,
                                              tankDirection=portTankDiesel1)
    portTankDiesel1_1ToportTankDiesel1.turnOn(voltage=200, turnovers=10)

    tankerShipGasole = Tank(tag='tankerShipGasole', volume=1000)
    portTankGasole = Tank(tag='portTankGasole', volume=2000)
    portTankGasole.fill = 1700

    portTankGasoleTotankerShipGasole = Pump(tag="portTankGasole-tankerShipGasole",
                                            allowTurnovers=20,
                                            allowVoltage=240,
                                            tankSource=portTankGasole,
                                            tankDirection=tankerShipGasole)
    portTankGasoleTotankerShipGasole.turnOn(voltage=100, turnovers=13)

    tankers += [tankerShipDiesel, portTankDiesel1, portTankDiesel1_1, tankerShipGasole, portTankGasole]
    pumpes += [portTankDiesel1ToTankerShipDiesel, portTankDiesel1_1ToportTankDiesel1, portTankGasoleTotankerShipGasole]


def runAsync():
    global pumpes, tankers
    """
    эмуляция процесса нефти перекачки в танкер

    Танкер заправяется в 3 емкости (Дизель, Бензин ) с помощью насосов и систем портовых цисцерн
    Загрузка танкера происходит до 100% с последовательными событиями Warning, Alarm.
    Далее танкер заменяется на новый

    :return:
    """

    init()

    while True:
        time.sleep(0.01)
        for p in pumpes:
            p.pump(0.01)

        for t in tankers:
            t.updateStatus()

        if any([t.status == STATUS_ALARM for t in tankers]):
            for p in pumpes:
                p.turnOff()

            print('stop pumping:', threading.current_thread())

            time.sleep(10)

            print('next tanker:', threading.current_thread())
            init()
