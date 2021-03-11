package destructive_wrapping.handlers;

import java.util.HashSet;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;


public class CatchClauseVisitor extends ASTVisitor {
	HashSet<CatchClause> dwoccurences = new HashSet<>();
	int count = 0;
	
	public boolean visit(CatchClause node) {
		if(isDestructive(node)) {
			dwoccurences.add(node);
			
			//count = count + 1;
		}
		
		return super.visit(node);
	}
	
	
	private boolean isDestructive(CatchClause node) {
		//return node.getBody().statements().toString().contains("throw new");
		String statementsload = node.getBody().statements().toString();
		//System.out.println(node.getE);
	
		if(statementsload.contains("throw new")) {
			return true;
		}
		else {
			return false;
		} 
		 
	}
	
	
	public HashSet<CatchClause> getdwoccurences() {
		return dwoccurences;
	}
	
	public int getdwoccurencescount() {
		return count;
	}
	
}