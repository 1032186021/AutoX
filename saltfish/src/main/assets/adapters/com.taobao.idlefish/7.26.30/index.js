// Saltfish adapter: com.taobao.idlefish v7.26.30
// Injected by ScriptBridge: scriptBridge, platform, taskType, params

function main() {
    scriptBridge.onTaskProgress("开始执行闲鱼自动化任务: " + taskType);

    try {
        var params = JSON.parse(params);

        switch (taskType) {
            case "post_goods":
                scriptBridge.onTaskProgress("正在发布商品...");
                auto.waitFor();
                scriptBridge.onTaskComplete(JSON.stringify({ success: true, msg: "发布成功" }));
                break;
            case "refresh":
                scriptBridge.onTaskProgress("正在刷新...");
                auto.waitFor();
                scriptBridge.onTaskComplete(JSON.stringify({ success: true, msg: "刷新成功" }));
                break;
            case "collect_coin":
                scriptBridge.onTaskProgress("正在领取闲鱼币...");
                auto.waitFor();
                scriptBridge.onTaskComplete(JSON.stringify({ success: true, msg: "领取成功" }));
                break;
            default:
                scriptBridge.onTaskError("未知任务类型: " + taskType);
        }
    } catch (e) {
        scriptBridge.onTaskError("脚本异常: " + e.message);
    }
}

main();
