Ext.define('Job.newjob', {
    extend: 'Ext.app.Application',
    name: 'Job',
    appFolder: CTX.PATH+'/job/app',
    autoCreateViewport: true,
    controllers: [
                  'JobCtrl'
    ]
});