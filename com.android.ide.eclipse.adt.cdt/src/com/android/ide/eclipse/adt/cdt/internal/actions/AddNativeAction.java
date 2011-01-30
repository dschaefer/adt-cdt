/*******************************************************************************
 * Copyright (c) 2010, 2011 Wind River Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Wind River Systems - Initial API and implementation
 *******************************************************************************/
package com.android.ide.eclipse.adt.cdt.internal.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.android.ide.eclipse.adt.cdt.internal.wizards.AddNativeWizard;

public class AddNativeAction implements IObjectActionDelegate {

	private IWorkbenchPart part;
	private ISelection selection;

	@Override
	public void run(IAction action) {
		IProject project = null;
		if (selection != null && selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection)selection;
			if (ss.size() == 1) {
				Object obj = ss.getFirstElement();
				if (obj instanceof IProject) {
					project = (IProject)obj;
				} else if (obj instanceof PlatformObject) {
					project = (IProject)((PlatformObject)obj).getAdapter(IProject.class);
				}
			}
		}
		
		if (project != null) {
			AddNativeWizard wizard = new AddNativeWizard(project, part.getSite().getWorkbenchWindow());
			WizardDialog dialog = new WizardDialog(part.getSite().getShell(), wizard);
			dialog.open();
		}
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection; 
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.part = targetPart;
	}

}
