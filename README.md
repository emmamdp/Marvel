# Marvel
 
![alt text](docs/images/marvel-comics.png)

**Marvel** es una app que muestra un listado de personajes y su detalle.

## Arquitectura y organización del proyecto
La arquitectura utilizada en este proyecto es MVVM, siguiendo los patrones SOLID.

El proyecto se ha estructurado en capas, teniendo las siguientes:

* **Presentation**
Esta capa está alojada bajo el módulo de app. Esta capa recoge los eventos de todas las interacciones que realice el usuario en la aplicación y también es la encargada de renderizar toda la información obtenida de la capa _domain_. A pesar de estar en la capa de app que visualiza los demás módulos (_domain_ y _data_), esta parte sólo mantiene contacto con la capa _domain_

* **Domain**
Se trata de un módulo propio dentro del proyecto y es el encargado de tener la lógica de negocio. Esta capa contiene los casos de uso, y es la encargada de hacer de vínculo entre la capa _data_ y _presentation_, ya que es el encargado de obtener los datos de _data_ y enviarlos a la capa _presentation_. Esta capa no visualiza a ninguna de las otras dos capas.

* **Data**
Se trata de un módulo propio dentro del proyecto. Esta capa contiene las librerías y frameworks que van a proveer la información necesaria a la aplicación. En esta capa tenemos las llamadas a apis con _Retrofit_ y también guardado en caché con _SharedPreferences_. Esta capa sólo visualiza la capa _domain_ para transmitirle la información que ha obtenido.


