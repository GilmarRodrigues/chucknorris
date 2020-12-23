# App ChuckNorris
Projeto Kotlin Android de amostra que usa a API [chuknorris.io](https://api.chucknorris.io/) e os componentes Jetpack.

O aplicativo lista as categorias das piadas de chucknorris, que são consulmidas da api, e possui uma camada de apresentação, onde o usuario pode esta favoritando a piada. 
Ao  marcar a piada como favorita, a mesma é armazenada internamente usando o Room.

# Bibliotecas usadas
Este exemplo apresenta os seguintes componentes de arquitetura:

- [Navigation](https://developer.android.com/guide/navigation)
- [View Binding](https://developer.android.com/topic/libraries/view-binding)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)
- [Koin](https://insert-koin.io/)
- [Room](https://developer.android.com/training/data-storage/room)
- [Retrofit](https://github.com/square/retrofit)
- [Picasso](https://square.github.io/picasso/)

# Aquitetura 
O aplicativo usa uma arquitetura [MVVM](https://developer.android.com/jetpack/guide) para a camada de apresentação.

Todas as transições de tela são feitas por [Navigation](https://developer.android.com/guide/navigation)
