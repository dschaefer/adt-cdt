package com.android.ide.eclipse.adt.cdt.internal;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.android.ide.eclipse.adt.cdt"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static Activator getDefault() {
		return plugin;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> clazz) {
		BundleContext context = plugin.getBundle().getBundleContext();
		ServiceReference ref = context.getServiceReference(clazz.getName());
		return (ref != null) ? (T)context.getService(ref) : null;
	}
	
	public static Bundle getBundle(String id) {
		for (Bundle bundle : plugin.getBundle().getBundleContext().getBundles()) {
			if (bundle.getSymbolicName().equals(id)) {
				return bundle;
			}
		}
		return null;
	}
	
	public static IStatus newStatus(Exception e) {
		return new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e);
	}
	
	public static void log(Exception e) {
		plugin.getLog().log(newStatus(e));
	}
	
	public static URL findFile(IPath path) {
		return FileLocator.find(plugin.getBundle(), path, null);
	}
	
}
