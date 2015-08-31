package io.leopard.command;

public interface IContextFactory {
	<E> E getBean(Class<E> c);

	Object getBean(String beanName);

	void exit();

	void shutDown(); 
}
