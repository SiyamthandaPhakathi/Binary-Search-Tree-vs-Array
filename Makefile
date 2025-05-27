all:
	mkdir -p bin/
	javac -d bin/ src/*.java

docs:
	mkdir -p doc/
	javadoc -d doc/ src/*.java

clean:
	rm -rf bin/ doc/

run:
	java -cp bin Main