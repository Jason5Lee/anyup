# anyup

Managing development environments for any programming language.

This project is in active development, see `dev` branch for the progress.

## Motivation

This project is inspired by [rustup](https://rustup.rs/). Rust uses `rustup` for quickly installing and updating its development environment, including the compiler, build system, and more. The principles behind `rustup` can be applied to many other scenarios. Therefore, this tool aims to adapt those ideas for setting up any type of development environment.

## Notes

This project is developed in Kotlin and will be compiled into a self-contained executable using the [Liberica Native Image Kit](https://bell-sw.com/pages/downloads/native-image-kit/). As a result, the final product can be run without any specific environment requirements.
