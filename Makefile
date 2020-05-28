all:
	javac src/Main.java -d classes/
	java -classpath classes/ Main
		
clean:
	rm -r classes/*
	rm -r Output/*