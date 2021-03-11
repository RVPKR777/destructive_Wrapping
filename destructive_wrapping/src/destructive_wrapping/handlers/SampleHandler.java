package destructive_wrapping.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;



import org.eclipse.core.resources.ResourcesPlugin;

public class SampleHandler extends AbstractHandler {
	
	//CatchClauseVisitor count = new CatchClauseVisitor();
	int count = 0;


	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();
		for(IProject project : projects) {
			try {
				System.out.println("Finding destructive exceptions in " + project.toString());
				Destructive_catcher(project);
				//System.out.println(count.getdwoccurencescount());
			}
			catch(CoreException e){
				e.printStackTrace();
			}
		}
		System.out.println("****Summary****");
		System.out.println("Project : "+ root.getName().toString());
		System.out.println("Destructive Wrapping : " + count);
		count = 0;
		
		return null;
	}
	
	
	public void Destructive_catcher(IProject project) throws JavaModelException {
		IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
		for(IPackageFragment mypackage : packages) {
			for(ICompilationUnit unit : mypackage.getCompilationUnits()) {
				CompilationUnit parsedCompilationUnit = parse(unit);
				CatchClauseVisitor exceptionVisitor = new CatchClauseVisitor();
				parsedCompilationUnit.accept(exceptionVisitor);
				printExceptions(exceptionVisitor);
				
			}
		}
		
	}
	
	
	private void printExceptions(CatchClauseVisitor visitor) {
		//int count = 0;
		//System.out.println("*************************************************************");
		for(CatchClause statement: visitor.getdwoccurences()) {
			System.out.println("*************************************************************\n");
			System.out.println(statement.toString());
			
		}
		count = count + visitor.getdwoccurences().size();
		//projectcount = visitor.getdwoccurences().size();
		//System.out.println(visitor.getdwoccurences().size());
		//System.out.println(count.getdwoccurencescount());
		
		
	}
	
	
	private CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS15);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setStatementsRecovery(true);
		return (CompilationUnit) parser.createAST(null);
	}
}
