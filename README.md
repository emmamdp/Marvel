# Marvel
 
![alt text](docs/images/marvel-comics.png)

**Marvel** es una app que muestra un listado de personajes y su detalle. Esta aplicación contiene lo siguiente:

* **Retrofit ->**
Para la conexión con las apis.

* **Shared Preference ->**
Para el guardado de datos en caché.

* **Koin ->**
Como inyector de dependencias.

* **Glide ->**
Para mostrar las imágenes al usuario.

* **Test unitarios ->**
Para el testeo de la funcionalidad de la app.

## Arquitectura y organización del proyecto
La arquitectura utilizada en este proyecto es **MVVM**, siguiendo los patrones **SOLID**.

El proyecto se ha estructurado en capas, siendo las siguientes:

* **Presentation:**
Esta capa está alojada bajo el módulo de app. En ella se recogen los eventos de todas las interacciones que realice el usuario en la aplicación, así como de renderizar toda la información obtenida de la capa _domain_. A pesar de estar en la módulo de _app_ (que visualiza los demás módulos _domain_ y _data_ ya que contiene la inicialización del inyector de dependencias _Koin_), sólo mantiene contacto con la capa _domain_.

* **Domain:**
Se trata de un módulo propio dentro del proyecto y es el encargado de la lógica de negocio. Esta capa contiene los casos de uso y hace de vínculo entre la capa _data_ y _presentation_, ya que obtiene los datos de _data_ y los envia a la capa _presentation_, o si la capa _presentation_ solicita algún tipo de información, _domain_ se lo comunica a _data_ y realice las acciones necesarias. Esta capa no visualiza a ninguna de las otras dos capas.

* **Data:**
Se trata de un módulo propio dentro del proyecto. Esta capa contiene las librerías y frameworks que van a proveer la información necesaria a la aplicación. En esta capa tenemos las llamadas a apis con _Retrofit_ y también guardado en caché con _SharedPreferences_. Esta capa sólo visualiza la capa _domain_ para transmitir o recibir información de la capa _presentation_.


