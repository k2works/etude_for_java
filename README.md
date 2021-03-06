[![CircleCI token](https://img.shields.io/circleci/project/github/k2works/etude_for_java/master.svg)](https://circleci.com/gh/k2works/etude_for_java/)

# Etude for Java
---

# 目的 #
Javaアプリケーションのための練習プログラム集

# 前提 #
| ソフトウェア   | バージョン   | 備考        |
|:---------------|:-------------|:------------|
| java           |1.8.0    |             |
| gradle         |4.2.1    |             |


# 構成 #
1. [構築](#構築)
1. [配置](#配置)
1. [運用](#運用)
1. [開発](#開発)

## 構築
仮想マシンのセットアップ
```bash
vagrant up
vagrant ssh
```
コンポーネントのセットアップ
```bash
curl -s "https://get.sdkman.io" | bash
sdk install java
sdk install gradle
gradle init
gradle buildEnvironment
```

**[⬆ back to top](#構成)**

## 配置
### CircleCi
```bash
mkdir .circleci
touch .circleci/config.yml
```
**[⬆ back to top](#構成)**

## 運用
**[⬆ back to top](#運用)**

## 開発
+ [FizzBuzz](./docs/fizzbuzz.md)
+ [テスト駆動開発](./docs/TDD.md)
+ [リファクタリング](./docs/refactoring.md)

**[⬆ back to top](#構成)**

