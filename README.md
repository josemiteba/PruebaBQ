APLICACIÓN CLIENTE DE EVERNOTE

Se ha desarrollado una aplicación que es cliente de Evernote utilizando el SDK de Evernote para Android:
https://github.com/evernote/evernote-sdk-android

Para usar este SDK, ha sido necesario obtener una API Key de https://dev.evernote.com/doc/

Además para tener permisos de desarrollo deberemos crear una cuenta en https://sandbox.evernote.com/Registration.action

Una vez hecho esto empezamos a configurar la aplicación:

- En el fichero build.gradle debemos añadir una dependencia de compilación:
    dependencies {
    compile 'com.evernote:android-sdk:2.0.0-RC3'
    }
- También necesitamos añadir lo siguiente en el AndroidManifest.xml para que la aplicación funciones correctamente:
    <activity android:name="com.evernote.client.android.EvernoteOAuthActivity" />
    <activity android:name="com.evernote.client.android.login.EvernoteLoginActivity"/>

La aplicación una vez abierta nos pedirá los credenciales para iniciar sesión en Evernote y una vez introducidos nos llevará a la pantalla con todas la notas que el usuario tiene.

Se puede añadir una nota nueva pulsando en el botón "+" y además se puede ver el contenido de una nota si se pulsa sobre ella.

(El resto de funciones no se han podido implementar por falta de tiempo).
