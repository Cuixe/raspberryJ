RaspberryJ Controlador de puerto GPIO.

Para que funcione debe estar instalado el modulo wiringpi en la raspberry

Desactivar IPv6 en raspbian

sudo nano /etc/sysctl.conf
net.ipv6.conf.all.disable_ipv6 = 1

sudo sysctl -p

Configurar IP fija

sudo nano /etc/dhcpcd.conf

interface eth0
static ip_address=192.168.1.{IP}/24
static routers=192.168.1.254
static domain_name_servers=192.168.1.254