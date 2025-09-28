# ポイント管理システム

Spring Bootを使った簡単なポイント管理Webアプリケーションです。

## 🎯 概要

- ユーザーのポイント残高管理
- ポイントの付与・利用機能
- 取引履歴の表示
- シンプルなWebインターフェース

## 🚀 起動方法

```bash
sudo dnf install java-17-amazon-corretto -y
sudo dnf install maven -y
mvn spring-boot:run
```

## 🌐 アクセス

- **メイン画面**: http://localhost:5555
- **H2コンソール**: http://localhost:5555/h2-console

### H2データベース接続情報
- JDBC URL: `jdbc:h2:mem:testdb`
- ユーザー名: `sa`
- パスワード: （空欄）

## 📋 機能

### Webインターフェース
- ユーザー選択（田中太郎/佐藤花子）
- ポイント残高表示
- ポイント付与
- ポイント利用
- 取引履歴表示

### REST API
- `GET /api/points/{userId}` - 残高照会
- `POST /api/points/add` - ポイント付与
- `POST /api/points/use` - ポイント利用
- `GET /api/points/{userId}/history` - 取引履歴

## 🛠 技術構成

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database**
- **HTML/CSS/JavaScript**
- **Bootstrap 5**

## 📁 プロジェクト構造

```
src/main/java/com/example/point/
├── PointApplication.java          # メインクラス
├── controller/PointController.java # REST API
├── entity/PointTransaction.java   # エンティティ
├── repository/PointRepository.java # データアクセス
└── service/PointService.java      # ビジネスロジック

src/main/resources/
├── static/                        # フロントエンド
│   ├── index.html
│   ├── app.js
│   └── style.css
├── application.properties         # 設定
└── data.sql                      # 初期データ
```

