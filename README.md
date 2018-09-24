# FactoryInMobile


Приложение для персонала на заводе для оценки работы установок и узлов на месте, сверка с реальными показателями. 

Приложение отображает: 
 *  [ ] Данные пользователя (Имя, Логин, Привелегии)  
 *  [X] Отображение текущей работы узлов на функциональной схеме
 *  [ ] Добавление пользователей для привелигированных 
 *  [ ] Демо режим для работы без интернета 
 *  [ ] Отображение карточек узлов, управление работой (Вкл/Выкл насоса)
 
  
Проект состоит из: 
 * FactoryInMobile - Android клиент
 * FactoryEmulatorAndServer - Python RestApi сервер с эмуляцией работы завода
 
 ## Запуск 
 Для запуска проекта необходимо выполнить следующие действия:
   * Запустить сервер 
          
          python FactoryInMobile/FactoryEmulatorAndServer/__init__.py
          
   * Подключить устройство к интернету в одну локальную сеть с сервером 
   * В коде Android проекта установить ip сервера [FactoryInMobile/FactoryInMobile/app/src/main/java/com/factory/andre/factoryinmobile/dagger/modules/HostModule.kt](https://github.com/AndreKuzubov/FactoryInMobile/blob/master/FactoryInMobile/app/src/main/java/com/factory/andre/factoryinmobile/dagger/modules/HostModule.kt)
   * Запустить Android проект
         
         
          
   
 
 ## License
```
Copyright (c) 2018 Andrey Kuzubov
```
