package soen6591tutorial1.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.JavaModelException;

import soen6591tutorial1.patterns.ExceptionFinder;


public class DetectException extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		SampleHandler.printMessage("root: " + root.getLocation().toOSString());
		IProject[] projects = root.getProjects();
		
		
		detectInProjects(projects);
		
		SampleHandler.printMessage("DONE DETECTING");
		
		return null;
	}

	private void detectInProjects(IProject[] projects) {
		for(IProject project : projects) {
			SampleHandler.printMessage("DETECTING IN: " + project.getName());
			ExceptionFinder exceptionFinder = new ExceptionFinder();
			
			
			try {
				
				exceptionFinder.findExceptions(project);
				
			} catch (JavaModelException e) {
				e.printStackTrace();
			}	
		}
	}
	
}
