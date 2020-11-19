// IWebviewProcessToMainProcessInterface.aidl
package com.hjdudu.webview;

import com.hjdudu.webview.ICallBack;

// Declare any non-default types here with import statements

interface IWebviewProcessToMainProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
  void handleWebCommand(String commandName, String jsonParams, in ICallBack callback);
}
