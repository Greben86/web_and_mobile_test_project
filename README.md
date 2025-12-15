# web_and_mobile_test_project

# Итоговый проект | Тестирование сервисов и приложений

## Тестирование веб-приложений

### Запуск тестов
Тесты запускаются из класса dev.greben.WebTestSample
В этом классе реализовано пять тестовых сценариев:
* Тест перехода на тематическую страницу разработки
* Тест перехода на страницу поиска
* Тест поиска организации
* Тест перехода на следующую страницу
* Тест перехода на страницу песочницы

## Тестирование мобильного приложения

### Установка мобильного приложения в эмулятор

Нужно скачать apk-файл приложения, например отсюда https://github.com/wikimedia/apps-android-wikipedia/releases/tag/latest
и установить в эмулятор
``` bash
~/Android/Sdk/platform-tools/adb install ~/Android/Sdk/platform-tools/app-alpha-universal-release.apk
```

### Установка и запуск Appium Server

Сначала установливаем Node.js
``` bash 
sudo apt update
sudo apt install curl build-essential libssl-dev zlib1g-dev libc-ares-dev libuv1-dev libxslt-dev python3
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash
source ~/.bashrc
nvm install lts/
nvm current
node -v
npm -v
```

Теперь можно установить сам Appium и драйвер uiautomator2
``` bash
sudo npm install -g appium
appium -v
appium driver install uiautomator2
appium driver list --installed
```

Нужно добавить переменные окружения
``` bash
export ANDROID_HOME=~/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

Теперь можно запустить сам Appium Server
``` bash
appium
```

### Запуск тестов
Тесты запускаются из класса dev.greben.MobileTestSample
В этом классе реализовано три сценария:
* Переход в настройки и добавление русского языка
* Поиск существующей статьи
* Поиск несуществующей статьи