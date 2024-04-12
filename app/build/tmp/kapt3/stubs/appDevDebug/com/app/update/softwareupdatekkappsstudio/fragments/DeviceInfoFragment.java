package com.app.update.softwareupdatekkappsstudio.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b;\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J&\u0010C\u001a\u0004\u0018\u00010D2\u0006\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010H2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001c\u0010!\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001c\u0010$\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001c\u0010\'\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001c\u0010*\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR\u001c\u0010-\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0006\"\u0004\b/\u0010\bR\u001c\u00100\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0006\"\u0004\b2\u0010\bR\u001c\u00103\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0006\"\u0004\b5\u0010\bR\u001c\u00106\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0006\"\u0004\b8\u0010\bR\u001c\u00109\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0006\"\u0004\b;\u0010\bR\u001c\u0010<\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0006\"\u0004\b>\u0010\b\u00a8\u0006I"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/fragments/DeviceInfoFragment;", "Landroidx/fragment/app/Fragment;", "()V", "tvBoard", "Landroid/widget/TextView;", "getTvBoard", "()Landroid/widget/TextView;", "setTvBoard", "(Landroid/widget/TextView;)V", "tvBrand", "getTvBrand", "setTvBrand", "tvBuildFingerPrint", "getTvBuildFingerPrint", "setTvBuildFingerPrint", "tvCompanyName", "getTvCompanyName", "setTvCompanyName", "tvDevice", "getTvDevice", "setTvDevice", "tvDeviceId", "getTvDeviceId", "setTvDeviceId", "tvDeviceManufacturer", "getTvDeviceManufacturer", "setTvDeviceManufacturer", "tvDeviceModel", "getTvDeviceModel", "setTvDeviceModel", "tvDeviceName", "getTvDeviceName", "setTvDeviceName", "tvDeviceName1", "getTvDeviceName1", "setTvDeviceName1", "tvDeviceType", "getTvDeviceType", "setTvDeviceType", "tvGAdvertisingId", "getTvGAdvertisingId", "setTvGAdvertisingId", "tvGSFId", "getTvGSFId", "setTvGSFId", "tvHardware", "getTvHardware", "setTvHardware", "tvHardwareSerial", "getTvHardwareSerial", "setTvHardwareSerial", "tvNetworkOperator", "getTvNetworkOperator", "setTvNetworkOperator", "tvNetworkType", "getTvNetworkType", "setTvNetworkType", "tvUsbDebugging", "getTvUsbDebugging", "setTvUsbDebugging", "tvWifiMacAddress", "getTvWifiMacAddress", "setTvWifiMacAddress", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "app_appDevDebug"})
public final class DeviceInfoFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvCompanyName;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvDeviceName;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvDeviceName1;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvDeviceModel;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvDeviceManufacturer;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvDevice;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvBoard;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvHardware;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvBrand;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvGSFId;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvGAdvertisingId;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvDeviceId;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvHardwareSerial;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvBuildFingerPrint;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvDeviceType;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvNetworkOperator;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvNetworkType;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvWifiMacAddress;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvUsbDebugging;
    
    public DeviceInfoFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvCompanyName() {
        return null;
    }
    
    public final void setTvCompanyName(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvDeviceName() {
        return null;
    }
    
    public final void setTvDeviceName(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvDeviceName1() {
        return null;
    }
    
    public final void setTvDeviceName1(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvDeviceModel() {
        return null;
    }
    
    public final void setTvDeviceModel(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvDeviceManufacturer() {
        return null;
    }
    
    public final void setTvDeviceManufacturer(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvDevice() {
        return null;
    }
    
    public final void setTvDevice(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvBoard() {
        return null;
    }
    
    public final void setTvBoard(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvHardware() {
        return null;
    }
    
    public final void setTvHardware(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvBrand() {
        return null;
    }
    
    public final void setTvBrand(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvGSFId() {
        return null;
    }
    
    public final void setTvGSFId(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvGAdvertisingId() {
        return null;
    }
    
    public final void setTvGAdvertisingId(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvDeviceId() {
        return null;
    }
    
    public final void setTvDeviceId(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvHardwareSerial() {
        return null;
    }
    
    public final void setTvHardwareSerial(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvBuildFingerPrint() {
        return null;
    }
    
    public final void setTvBuildFingerPrint(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvDeviceType() {
        return null;
    }
    
    public final void setTvDeviceType(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvNetworkOperator() {
        return null;
    }
    
    public final void setTvNetworkOperator(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvNetworkType() {
        return null;
    }
    
    public final void setTvNetworkType(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvWifiMacAddress() {
        return null;
    }
    
    public final void setTvWifiMacAddress(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTvUsbDebugging() {
        return null;
    }
    
    public final void setTvUsbDebugging(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
}