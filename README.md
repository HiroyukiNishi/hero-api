# hero-api

aws lambda create-function \
 --region us-east-1 \
 --function-name lambda-heroes \
 --zip-file fileb://target/scala-2.11/aws-lambda-scala-0.1-SNAPSHOT.jar \
 --role arn:aws:iam::************:role/Administrator \
 --handler lambda.hero.HeroesController::handleRequest \
 --runtime java8 \
 --timeout 15 \
 --memory-size 512

## JAR??????
sbt assembly