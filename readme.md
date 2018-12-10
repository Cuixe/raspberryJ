#RaspberryJ Controlador de puerto GPIO.

## Preparación de la raspberry

- Copiar la llave SSH del equipo local en la raspberry.
- Instalar el módulo wiringpi
- Desacrivar la versión 6 del protocolo IP
```
    sudo nano /etc/sysctl.conf
    net.ipv6.conf.all.disable_ipv6 = 
```
 Y luego ejecutar:
```
    sudo sysctl -p
```
- Configurar IP fija:
```
    sudo nano /etc/dhcpcd.conf
    
    interface eth0
    static ip_address=192.168.1.{IP}/24
    static routers=192.168.1.254
    static domain_name_servers=192.168.1.254
```

## Instalación:

Ejecutar shell publish.sh {user} {IP}

### Nota:
*En el shell de arranque se tomara el valor {IP} para asignar la IP JMX en el script de arranque*