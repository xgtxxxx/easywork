Ext.application({
    name: 'Job',
    // automatically create an instance of Task.view.Viewport
    autoCreateViewport: true,
    appFolder : CTX.PATH+'/job/app',
    controllers: [
        'JobCtrl'
    ]
});
