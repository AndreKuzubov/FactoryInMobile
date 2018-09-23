STATUS_STABLE = "stable"
STATUS_WARNING = "warning"
STATUS_ALARM = "alarm"


class Tank:

    def __init__(self, tag, volume):
        self.volume = volume
        self.fill = 0
        self.tag = tag
        self.status = STATUS_STABLE

    def updateStatus(self):
        if self.fill > self.volume * 0.9:
            self.status = STATUS_ALARM
        elif self.fill > self.volume * 0.9:
            self.status = STATUS_WARNING
        else:
            self.status = STATUS_STABLE

    def toDict(self):
        return self.__dict__
