package com.cllobet.xposedhooks;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import dalvik.system.DexFile;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XposedHelpers.ClassNotFoundError;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.callbacks.XCallback;

public class TraceClass {

	public TraceClass() {
		// TODO Auto-generated constructor stub
	}
	
  public void traceFunction (final String className, final String funcName, LoadPackageParam lpparam, Object... objects ) {
        final Class<?> clazzHook = XposedHelpers.findClass(className, lpparam.classLoader);
        XposedHelpers.findAndHookMethod(clazzHook, funcName, objects, new XC_MethodHook(){
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {   
                Log.i("XPOSED", className+"."+funcName+" => " + param.getResult().toString());
            }
        });
    }

    public boolean traceClass (final String className, LoadPackageParam lpparam)
    {

        //   if(!IS_TRACE_ENABLED) return false;

        boolean ret = true;
        try {
            final Class<?> classForTrace = XposedHelpers.findClass(className, lpparam.classLoader);
            if (classForTrace != null) {
                try {
                    XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            // hook all methods
                            for (final Method method : classForTrace.getDeclaredMethods()) {
                                if (!Modifier.isAbstract(method.getModifiers()) && !Modifier.isNative(method.getModifiers()) && method.getName() != "invoke" ) {
                                    XposedBridge.hookMethod(method, new XC_MethodHook(XCallback.PRIORITY_LOWEST) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            String res = "";

                                            if(((Method) method).getReturnType().equals(Void.TYPE)){
                                                res = "VOID";
                                            } else {
                                                if(param.getResult() != null) res = param.getResult().toString();
                                                else res = "NULL";
                                            }

                                            String args = "";
                                            if (param == null)
                                            {
                                                return;
                                            }
                                            if (param.args != null){
                                                for (int i=0; i < param.args.length; i++)
                                                {
                                                    if (param.args[i] != null)
                                                    {
                                                        if(i > 0) args += ",";
                                                        args += "\""+param.args[i].toString()+"\"";
                                                    }
                                                }
                                            }

                                            Log.i("XPOSED", classForTrace.getName() + "." + method.getName() + "(" + args + ")" + " => result: " + res);
                                            //Utils.logMethodCall(className, param);
                                        }
                                    });
                                    //Log.d("XPOSED", "method " + method + " hooked");
                                }
                            }
                        }
                    });
                }


                catch (NoSuchMethodError e)
                {
                    Log.d("XPOSED", "some method was not found." + e.getMessage());
                    ret = false;
                }

            }
            else
            {
                Log.d("XPOSED", classForTrace.getName() + " not found in " + lpparam.packageName);
            }


        } catch (ClassNotFoundError ex) {
            Log.d("XPOSED", "some method was not found.");
            ret = false;
        } catch (NoClassDefFoundError ex2) {
            ret = false;
        }


        return ret;
    }
 
  public String[] getClassesOfPackage(LoadPackageParam lpparam) {
      ArrayList<String> classes = new ArrayList<String>();
      try {
          String packageCodePath = lpparam.appInfo.sourceDir;//getPackageCodePath();
          DexFile df = new DexFile(packageCodePath);
          for (Enumeration<String> iter = df.entries(); iter.hasMoreElements(); ) {
              String className = iter.nextElement();
              //if (className.contains(lpparam.packageName)) {
                  //classes.add(className.substring(className.lastIndexOf(".") + 1, className.length()));
              //}
              classes.add(className);
          }
      } catch (IOException e) {
          e.printStackTrace();
      }

      return classes.toArray(new String[classes.size()]);
  }
}
