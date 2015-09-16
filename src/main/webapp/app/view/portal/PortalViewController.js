Ext.define('app.view.portal.PortalViewController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.portal',

    onAddFeed: function () {
        var dashboard = this.lookupReference('dashboard');
        dashboard.addNew('rss');
    },

    onAddFeedUrl: function (sender) {
        var dashboard = this.lookupReference('dashboard');

        dashboard.addView({
            type: 'rss',
            feedUrl: sender.feedUrl
        });
    }
});
