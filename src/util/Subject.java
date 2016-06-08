package util;

public interface Subject {
	public void notifyObservers(String message);

	public void registerObserver(Observer observer);

	public void removeObserver(Observer observer);
}
