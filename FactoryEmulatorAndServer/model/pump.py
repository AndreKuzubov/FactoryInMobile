import copy


class Pump:
    def __init__(self, tag, allowVoltage, allowTurnovers, tankSource, tankDirection):
        self.voltage = 0
        self.turnovers = 0
        self.allowVoltage = allowVoltage
        self.allowTurnovers = allowTurnovers
        self.tag = tag

        self.tankSource = tankSource
        self.tankDirection = tankDirection

    def pump(self, timeDelta):
        intake = self.turnovers * timeDelta

        intake = min(self.tankSource.fill, intake)

        self.tankSource.fill -= intake
        self.tankDirection.fill += intake

    def turnOn(self, voltage, turnovers):
        self.turnOn = True
        self.voltage = voltage
        self.turnovers = turnovers

    def turnOff(self):
        self.turnOn = False
        self.voltage = 0
        self.turnovers = 0

    def toDict(self):
        d = copy.copy(self.__dict__)
        d['tankSource'] = self.tankSource.tag
        d['tankDirection'] = self.tankDirection.tag
        return d
