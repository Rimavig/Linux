# Linux
PROYECTO SENSOR SISMICO 

Nuestra página web está alojada en un servidor de Amazon (AWS) con dirección pública 18.217.220.228 para realizar el Frontend se utilizó php, y para la parte interna se realizó mediante programación en lenguaje Java, la página web es la que interactúa el usuario para observar los sismos que se han generado en el año, esta página web consta de un reverse proxy para garantizar la seguridad de la página, todo interacción que tenga el usuario con la página web se comunica con el microservicio mediante comunicación binaria, utilizando un GRPS(Apache Thrift), el microservicio se encarga de enviar mensajes de correo, generar un Tweet y actualizar la página web, este microservicio se conecta a la base de datos para observar la información que emiten los 3 sensores y dar alertas de sismos si los 3 sensores han sido activados, la parte de cache la utilizamos para optimizar el tiempo, entre mas rápido sea este proceso, mayor será la eficiencia del servicio 

El usuario sólo debe decidir en qué puntos desea colocar los sensores y poseer acceso a internet para conectar los sensores, una vez hecho esto y dar sus respectivas credenciales de twitter y correo, el usuario dispondrá de todas estas opciones y ventajas. 

Accediendo al sitio http://18.217.220.228/sismo.php podremos visualizar cuando se haya efectuado un movimiento sísmico, la página trabaja en tiempo real y está levantada en AWS.

Recibiremos una alerta en nuestro correo gmail, la configuración de este servicio está explicado en la sección de códigos. 

Con el microservicio para enlazar a twitter las alertas, se publicará en una cuenta en tiempo real cuando se envía la señal, el funcionamiento de esto está explicado en la sección de códigos

 

