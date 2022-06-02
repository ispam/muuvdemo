# Muuv Demo

![1](https://github.com/ispam/muuvdemo/tree/master/gif/muuvdemo.gif)

**Objective:** Build a REST app to show users and its details

## Architectural Pattern

** Model View ViewModel (MVVM) **

The architectural pattern are presented by modules:
-   *Common Module:* This module is in charge to hold all the repeatable info used by the other modules
-   *Di Module:* This module is responsible for dealing with all the dependency injections
-   *UI Module:* This module is for all the UI components like splash screen and main screen
-   *Utils Module:* This module holds all utility classes and extensions

## Libraries
- [Dagger Hilt](https://dagger.dev/hilt/) by Google
- [Retrofit](https://github.com/square/retrofit) by Square
- [okHTTP](https://github.com/square/okhttp) by Square
- [Picasso](https://github.com/square/picasso) by Square

## License

Copyright [2022] [Diego Fernando Urrea Gutiérrez]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.