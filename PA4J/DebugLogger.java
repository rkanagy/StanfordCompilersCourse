import java.util.logging.*;

public class DebugLogger {
	private static DebugLogger instance = null;
	private static Logger logger = Logger.getLogger("CoolSemanticAnalyzer");
	
	public static DebugLogger Instance() {
		if (instance == null) {
			instance = new DebugLogger();
			setupLogger();
		}
		return instance;
	}
	
	public void logMessage(String msg) {
		logger.log(Level.INFO, msg);
	}
	
	public void setLoggingOn() {
		logger.setLevel(Level.INFO);
	}
	
	private static void setupLogger() {
		// suppress default logging output to the console
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) {
			rootLogger.removeHandler(handlers[0]);
		}
		
		logger.setLevel(Level.OFF); // logging turned off by default
		ConsoleHandler handler = new ConsoleHandler();
		MyPlainTextFormatter formatter = new MyPlainTextFormatter();
		handler.setFormatter(formatter);
		logger.addHandler(handler);
	}
}

class MyPlainTextFormatter extends Formatter {

	@Override
	public String format(LogRecord rec) {
		return rec.getMessage() + "\n";
	}
	
}