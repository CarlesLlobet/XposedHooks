/**
 * 
 */
package com.cllobet.xposedhooks;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import javax.net.ssl.SSLSession;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XposedHelpers.ClassNotFoundError;
import de.robv.android.xposed.callbacks.XCallback;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * @author sven
 *
 */
public class XposedHooks implements IXposedHookLoadPackage {
	public static final String packagename = "com.example.cllobet.ndklogin"; //For example: com.example.cllobet.ndklogin
	
	
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
		XposedBridge.log("Loaded app: " + lpparam.packageName);

		if (!lpparam.packageName.equals(packagename)) return; 

		//this.traceClass("com.example.cllobet.ndklogin", lpparam);
		
		/*
		 * Hooking a method
		 */
		/* Hooking smali2 */
		/*
		XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                // Hook here (as normal) using lpp.classLoader
            }
        });
		*/
		
		/* ROOT DETECTION BYPASS (Buscar "root" en los smali) */
		
//		final Class<?> BypassRD = XposedHelpers.findClass("com.example.cllobet.ndklogin.SecurityManagerImpl", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(BypassRD, "isDeviceRooted", new XC_MethodHook(){
//			@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//            	//Modifying the return value
//            	param.setResult(false);
//            	Log.i("XPOSED", "isDeviceRooted1 Bypassed");
//			}
//		});
//
//		final Class<?> BypassRD2 = XposedHelpers.findClass("com.example.cllobet.ndklogin.SecurityManager", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(BypassRD2, "isDeviceRooted", new XC_MethodHook(){
//			@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//            	//Modifying the return value
//            	param.setResult(false);
//            	Log.i("XPOSED", "isDeviceRooted2 Bypassed");
//			}
//		});
//
//		final Class<?> BypassRD3 = XposedHelpers.findClass("com.example.cllobet.ndklogin.DeviceUtils", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(BypassRD3, "checkRootMethod1", new XC_MethodHook(){
//			@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//            	//Modifying the return value
//            	param.setResult(false);
//            	Log.i("XPOSED", "checkRootMethod1 Bypassed");
//			}
//		});
//
//		final Class<?> BypassRD4 = XposedHelpers.findClass("com.example.cllobet.ndklogin.DeviceUtils", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(BypassRD4, "checkRootMethod2", new XC_MethodHook(){
//			@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//            	//Modifying the return value
//            	param.setResult(false);
//            	Log.i("XPOSED", "checkRootMethod2 Bypassed");
//			}
//		});
//
//		final Class<?> BypassRD5 = XposedHelpers.findClass("com.example.cllobet.ndklogin.DeviceUtils", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(BypassRD5, "checkRootMethod3", new XC_MethodHook(){
//			@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//            	//Modifying the return value
//            	param.setResult(false);
//            	Log.i("XPOSED", "checkRootMethod3 Bypassed");
//			}
//		});
//
//		final Class<?> BypassRD6 = XposedHelpers.findClass("com.example.cllobet.ndklogin.DeviceUtils", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(BypassRD6, "checkRootMethod4", new XC_MethodHook(){
//			@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//            	//Modifying the return value
//            	param.setResult(false);
//            	Log.i("XPOSED", "checkRootMethod4 Bypassed");
//			}
//		});
//
//		final Class<?> BypassRD7 = XposedHelpers.findClass("com.example.cllobet.ndklogin.DeviceUtils", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(BypassRD7, "isDeviceRooted", Context.class, new XC_MethodHook(){
//			@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//            	//Modifying the return value
//            	param.setResult(false);
//            	Log.i("XPOSED", "isDeviceRooted3 Bypassed");
//			}
//		});
//
//		/* SSL PINNING BYPASS (Buscar "final verify(L" en los smali) */
//		// Hookeamos attach para luego encontrar las clases dentro de los classes2.dex
//
//		XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//            	// Certificate Pinning Bypass
//            	// Hook here (as normal) using lpp.classLoader
//            	final Class<?> ClassToHook6 = XposedHelpers.findClass("o.axr", lpparam.classLoader);
//                XposedHelpers.findAndHookMethod(ClassToHook6, "verify", String.class, SSLSession.class, new XC_MethodHook(){
//
//                	@Override
//                	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                		// TODO Auto-generated method stub
//                		super.afterHookedMethod(param);
//
//                		param.setResult(true);
//        				Log.i("XPOSED", "verify axr Bypassed");
//
//                	}
//                });
//
//        		final Class<?> ClassToHook = XposedHelpers.findClass("o.ayb", lpparam.classLoader);
//                XposedHelpers.findAndHookMethod(ClassToHook, "verify", String.class, SSLSession.class, new XC_MethodHook(){
//
//                	@Override
//                	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                		// TODO Auto-generated method stub
//                		super.afterHookedMethod(param);
//
//                		param.setResult(true);
//        				Log.i("XPOSED", "verify ayb Bypassed");
//
//                	}
//                });
//
//                final Class<?> ClassToHook1 = XposedHelpers.findClass("o.qi", lpparam.classLoader);
//                XposedHelpers.findAndHookMethod(ClassToHook1, "verify", String.class, SSLSession.class, new XC_MethodHook(){
//
//                	@Override
//                	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                		// TODO Auto-generated method stub
//                		super.afterHookedMethod(param);
//
//                		param.setResult(true);
//        				Log.i("XPOSED", "verify qi Bypassed");
//
//                	}
//                });
//
//            }
//        });
//
//		final Class<?> ClassToHook2 = XposedHelpers.findClass("com.example.cllobet.ndklogin.ModifiedHostnameVerifier", lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(ClassToHook2, "verify", String.class, SSLSession.class, new XC_MethodHook(){
//
//        	@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//        		// TODO Auto-generated method stub
//        		super.afterHookedMethod(param);
//
//        		param.setResult(true);
//				Log.i("XPOSED", "verify ModifiedHostname Bypassed");
//
//        	}
//        });
        
        
        //Buscar "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;" + "HARDWARE"
        
//        final Class<?> ClassToHook4 = XposedHelpers.findClass("o.bJe", lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(ClassToHook4, "\u02cb", Context.class, String.class, String.class, new XC_MethodHook(){
//                    	
//        	@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//        		// TODO Auto-generated method stub
//        		super.afterHookedMethod(param);
//        		
//        		//String args1 = (String) param.args[0];
//				String args2 = (String) param.args[1];
//        		String args3 = (String) param.args[2];
//        		String res = (String) param.getResult();
//        		//c7e4dd6d84f1042d:universal3470:520065eaba742275:com.imaginbank.app:androidPhone
//        		Log.i("XPOSED", "AppID: " +args2+ "\nVariant: "+args3+"\nResultado Cifrado: " + res);
//        		
//        	}
//        });
//        
//        XposedHelpers.findAndHookMethod(ClassToHook4, "\u0971", Context.class, String.class, String.class, new XC_MethodHook(){
//        	
//        	@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//        		// TODO Auto-generated method stub
//        		super.afterHookedMethod(param);
//        		
//        		//String args1 = (String) param.args[0];
//				String args2 = (String) param.args[1];
//        		String args3 = (String) param.args[2];
//        		String res = (String) param.getResult();
//        		//c7e4dd6d84f1042d:universal3470:520065eaba742275:com.imaginbank.app:androidPhone
//        		Log.i("XPOSED", "AppID: " +args2+ "\nVariant: "+args3+"\nResultado Cifrado: " + res);
//        		
//        	}
//        });
//        
//        XposedHelpers.findAndHookMethod(ClassToHook4, "\u02ca", String.class, new XC_MethodHook(){
//        	
//        	@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//        		// TODO Auto-generated method stub
//        		super.afterHookedMethod(param);
//        		
//        		//String args1 = (String) param.args[0];
//				String args2 = (String) param.args[0];
//        		String res = (String) param.getResult();
//        		Log.i("XPOSED", "\nMetodo de cifrado.\nString A Cifrar: " +args2+ "\n String cifrado con SHA1: "+ res);
//        		//Log.i("XPOSED", "Metodo de cifrado.");
//        		//Log.i("XPOSED", "\nIdentificador: " +args2);
//        		//Log.i("XPOSED", "\nandroid_id: c7e4dd6d84f1042d");
//        		//Log.i("XPOSED", "\nHARDWARE: universal3470");
//        		//Log.i("XPOSED", "\nSERIAL: 520065eaba742275");
//        		//Log.i("XPOSED", "\nAppID: com.imaginbank.app");
//        		//Log.i("XPOSED", "\nVariant: androidPhone");
//        		//Log.i("XPOSED", "\nString cifrado con SHA1: "+ res);
//	
//        	}
//        });
        /*
        final Class<?> ClassToHook6 = XposedHelpers.findClass("o.aki", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(ClassToHook6, "\u0971", Context.class, new XC_MethodHook(){
                    	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		String res = (String) param.getResult();

        		Log.i("XPOSED", "aki Resultado Cifrado: " + res);
        		
        	}
        });
        
        final Class<?> ClassToHook7 = XposedHelpers.findClass("o.bbX", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(ClassToHook7, "\u0971", new XC_MethodHook(){
                    	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		String res = (String) param.getResult();

        		Log.i("XPOSED", "bbX: " + res);
        		
        	}
        });
        
        final Class<?> ClassToHook8 = XposedHelpers.findClass("o.\u01ad", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(ClassToHook8, "�?", Context.class, new XC_MethodHook(){
                    	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		String res = (String) param.getResult();

        		Log.i("XPOSED", "01ad: " + res);
        		
        	}
        });
        
        final Class<?> ClassToHook9 = XposedHelpers.findClass("o.aUL", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(ClassToHook9, "ˊ", Context.class, new XC_MethodHook(){
                    	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		String res = (String) param.getResult();

        		Log.i("XPOSED", "aUL: " + res);
        		
        	}
        });
        
        final Class<?> ClassToHook10 = XposedHelpers.findClass("o.bV", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(ClassToHook10, "\u0971", new XC_MethodHook(){
                    	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		String res = (String) param.getResult();

        		Log.i("XPOSED", "bV: " + res);
        		
        	}
        });
        */
        
        //PIN
        /*
        final Class<?> ClassToHook11 = XposedHelpers.findClass("o.beS", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(ClassToHook11, "�?", String.class, new XC_MethodHook(){
                    	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		String arg = (String) param.args[0];

        		Log.i("XPOSED", "usuario beS: " + arg);
        		
        	}
        });
        
        XposedHelpers.findAndHookMethod(ClassToHook11, "ˋ", String.class, new XC_MethodHook(){
                    	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		String arg = (String) param.args[0];

        		Log.i("XPOSED", "pin beS: " + arg);
        		
        	}
        });
        
        final Class<?> ClassToHook12 = XposedHelpers.findClass("o.beQ", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(ClassToHook12, "ˊ", new XC_MethodHook(){
                    	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		String res = (String) param.getResult();

        		Log.i("XPOSED", "String Res beQ: " + res);
        		
        	}
        });
        
        XposedHelpers.findAndHookMethod(ClassToHook12, "ॱ", new XC_MethodHook(){
        	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		String res = (String) Integer.toString((Integer) param.getResult());

        		Log.i("XPOSED", "Int Res beQ: " + res);
        		
        	}
        });
        */
        
//        final Class<?> ClassToHook4 = XposedHelpers.findClass("o.\u14fa", lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(ClassToHook4, "ˊ", android.content.pm.PackageManager.class, new XC_MethodHook(){
//                    	
//        	@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//        		// TODO Auto-generated method stub
//        		super.afterHookedMethod(param);
//        		
//        		param.setResult(true);
//				Log.i("XPOSED", "Primer PM Packages Bypassed");
//        		
//        	}
//        });
//        final Class<?> ClassToHook5 = XposedHelpers.findClass("o.\u14fa", lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(ClassToHook5, "ˋ", android.content.pm.PackageManager.class, new XC_MethodHook(){
//                    	
//        	@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//        		// TODO Auto-generated method stub
//        		super.afterHookedMethod(param);
//        		
//        		param.setResult(true);
//				Log.i("XPOSED", "Segundo PM Packages Bypassed");
//        		
//        	}
//        });
        
        
//        //GetUsers Users[.....]
//        final Class<?> ClassToHook4 = XposedHelpers.findClass("com.example.cllobet.ndklogin.ConfigurationBean", lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(ClassToHook4, "getUsers", new XC_MethodHook(){
//                    	
//        	@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//        		// TODO Auto-generated method stub
//        		super.afterHookedMethod(param);
//        		
//        		String[] res = (String[]) param.getResult();
//				Log.i("XPOSED", "getUsers result ==> " + Arrays.toString(res));
//        		
//        	}
//        });
//        
//        //Antes del GetUsers el v3 este qe tiene?
//        final Class<?> ClassToHook5 = XposedHelpers.findClass("o.aJt", lpparam.classLoader);
//        XposedHelpers.findAndHookMethod(ClassToHook5, "ˊ", String.class, int.class, String.class, new XC_MethodHook(){
//                    	
//        	@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//        		// TODO Auto-generated method stub
//        		super.afterHookedMethod(param);
//        		
//        		String args1 = (String) param.args[0];
//				int args2 = (Integer) param.args[1];
//        		String args3 = (String) param.args[2];
//        		
//        		String res = (String) param.getResult();
//				
//				Log.i("XPOSED", "v3 ==> Param1: " +args1+ " Param2: "+args2+" Param3: "+args3+ " Resultado: " + res);
//        		
//        	}
//        });
        
        
        
        /* BIZUM BYPASS */
        /*
        final Class<?> bizzumBypass = XposedHelpers.findClass("o.wD", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(bizzumBypass, "ʽ", new XC_MethodHook(){
                    	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		param.setResult(true);
				Log.i("XPOSED", "[BIZUM] o.wD Called");
        	}
        });
        
        
        final Class<?> bizzumBypass2 = XposedHelpers.findClass("com.example.cllobet.ndklogin.ConfigurationBean$Apartado", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(bizzumBypass2, "getEnabled", new XC_MethodHook(){
                    	
        	@Override
        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		// TODO Auto-generated method stub
        		super.afterHookedMethod(param);
        		
        		param.setResult("true");
				Log.i("XPOSED", "[BIZUM] getEnabled Called");
        		
        	}
        });
		*/
        
        /*
		 * Modifying the return value
		 */
//		final Class<?> BypassDebugHook = XposedHelpers.findClass("com.example.cllobet.ndklogin.HCEApplication", lpparam.classLoader);
//      XposedHelpers.findAndHookMethod(BypassDebugHook, "isFirstUsePref", new XC_MethodHook(){
//        	@Override
//        	protected void afterHookedMethod(MethodHookParam param) throws Throwable {

            	//Modifying the return value
//            	param.setResult(true);
//			}
//		});
        
        
//      final Class<?> SuccessHook = XposedHelpers.findClass("bbbbbb.bbbbkb", lpparam.classLoader);
        /*
		 * Uno de los parametros es una clase propia, por lo tanto la guardamos en una variable para pasarsela por parametro
		 */
//      final Class<?> RetrofitHook = XposedHelpers.findClass("retrofit.client.Response", lpparam.classLoader);
//      XposedHelpers.findAndHookMethod(SuccessHook, "success", Object.class, RetrofitHook, new XC_MethodHook(){
//          @Override
//          protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//              Log.i("XPOSED", "Bypassed");
//          }
//      });
                					
		
		/*
		 * Mostrando los 2 parametros y el resultado de la funcion, se usa el Unicode ya que tanto la clase como el metodo son caracteres raros
		 */
//        final Class<?> ClassToHook1 = XposedHelpers.findClass("o.\uFE97", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(ClassToHook1, "\u02CA", String.class, String.class, new XC_MethodHook(){
//			@Override
//			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//				String args1 = (String) param.args[0];
//				String args2 = (String) param.args[1];
//				String res = (String) param.getResult();
//				
//				Log.i("XPOSED", "String1: "+args1+", String2: "+args2+" => " + res);
//			}
//		});
		
		/*
		 * Haciendo Bypass del Certificate Pinning (función verify)
		 */
//		final Class<?> IVclass = XposedHelpers.findClass("o.IV", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(IVclass, "verify", String.class, SSLSession.class, new XC_MethodHook(){
//			@Override
//			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//				param.setResult(true);
//			}
//		});
		
		
		
		/*
		 * Bypass of root detection I: (new File("/system/app/Superuser.apk")).exists()
		 */
//		XposedHelpers.findAndHookMethod(File.class, "exists", new XC_MethodHook() {
//			@Override
//			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//				File f = (File)param.thisObject;
//				if(f.getAbsolutePath().equals("/system/app/Superuser.apk")) {
//					param.setResult(false);
//				}
//			}
//		});
//		
//		/*
//		 * Bypass of root detection II: o.\u0294.\u02CA.("exec", Runtime.getRuntime(), null, "su")
//		 */
//		final Class<?> execSuHook = XposedHelpers.findClass("o.\u0294", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(execSuHook, "\u02CA", String.class, Runtime.class, Class[].class, Object[].class, new XC_MethodHook(){
//			@Override
//			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//				String func =  (String) param.args[0];
//				String[] args = (String[]) ((Object[]) param.args[3])[0];
//				if(args.length == 1 && args[0].equals("su")) {
//					((String[]) ((Object[]) param.args[3])[0])[0] = "sus"; //Using sus as a fake program
//				}
//			}
//		});
//		
//		
//		/*
//		 * Bypass Certificate Pinning: Changing the result of the HostnameVerifier implementation for the verify function
//		 */
//		final Class<?> verifyBypass = XposedHelpers.findClass("o.\u14DB", lpparam.classLoader);
//		XposedHelpers.findAndHookMethod(verifyBypass, "verify", String.class, SSLSession.class, new XC_MethodHook(){
//			@Override
//			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//				param.setResult(true);
//			}
//		});
//		

	}
}
