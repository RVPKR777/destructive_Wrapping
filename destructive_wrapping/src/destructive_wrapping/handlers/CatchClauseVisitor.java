package destructive_wrapping.handlers;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;

public class CatchClauseVisitor extends ASTVisitor {
	HashSet<CatchClause> dwoccurences = new HashSet<>();
	
	public boolean visit(CatchClause node) {
		if(isDestructive(node)) {
			dwoccurences.add(node);
		}
		
		return super.visit(node);
	}
	
	
	private boolean isDestructive(CatchClause node) {
		return node.getBody().statements().toString().contains("throw new");
	}
	
	
	public HashSet<CatchClause> getdwoccurences() {
		return dwoccurences;
	}
	
}