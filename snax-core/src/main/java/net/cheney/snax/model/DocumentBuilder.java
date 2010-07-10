package net.cheney.snax.model;

import javax.annotation.Nonnull;


public final class DocumentBuilder extends NodeBuilder {
	
	private final NamespaceMap declaredNamespaces = new NamespaceMap(3);
	
	public DocumentBuilder() {
		declaredNamespaces.put(Namespace.NO_NAMESPACE.prefix(), Namespace.NO_NAMESPACE);
		declaredNamespaces.put(Namespace.XML_NAMESPACE.prefix(), Namespace.XML_NAMESPACE);
		declaredNamespaces.put(Namespace.XMLNS_NAMESPACE.prefix(), Namespace.XMLNS_NAMESPACE);
	}

	@Override
	public void doAttributeName(@Nonnull CharSequence seq) {
		throw new IllegalStateException(String.format("Unable to add attribute name [%s]", seq));
	}

	@Override
	public void doAttributeValue(@Nonnull CharSequence seq) {
		throw new IllegalStateException(String.format("Unable to add attribute value [%s]", seq));
	}

	@Override
	public void doCharacters(@Nonnull CharSequence seq) {
		throw new IllegalStateException(String.format("Unable to add characters value [%s]", seq));
	}

	@Override
	public NodeBuilder doElementEnd() {
		throw new IllegalStateException();
	}

	@Override
	protected Namespace declaredNamespaceForPrefix(@Nonnull String prefix) {
		return declaredNamespaces.get(prefix);
	}

	public Document build() {
		return new Document(contents());
	}

}
