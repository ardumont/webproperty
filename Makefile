VERSION=0.1.0-SNAPSHOT

jar:
	lein jar

uberjar:
	lein uberjar

run-dev:
	lein run

run:
	java -Dbootstrap.webproperty=/home/tony/repo/perso/webproperty/resources/public/bootstrap-webproperty.properties -jar ./target/webproperty-$(VERSION)-standalone.jar
