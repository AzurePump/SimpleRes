package com.github.coaixy

import com.sun.net.httpserver.Authenticator.Success
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.subCommand

//指令
//sres help
//sres create <领地名称> -用锄头创建领地
//sres delete <领地名称> 删除领地
//sres add_player <领地名称> <玩家名称> -赋予其他玩家进入领地权限且拥有任何权限
//sres delete_player <领地名称> <玩家名称> -反之同上
//sres entry <领地名称> <true or false> 是否允许任何玩家进入领地（仅仅是进入，没有其他权限），被add的玩家不受影响
//sres use <领地名称> <true or false> 是否允许进入领地的任何玩家使用 炉子 工作台等(不包括箱子)，被add的玩家不受影响
//sres tpset - 设置当前领地的传送点为你站立的地方
//sres tp <领地名称> 仅限创建者和被add的玩家，允许使用tp进入领地（需要先用tpset设置传送点，不然不能用）
//sres list 查看领地列表（包含被add的，自己创建的和被add的分开）
//
//config.yml
//
//1. 设置每个人 最大创建领地数量
//2 。设置领地最大范围 长宽高
//3. 设置领地最小范围 长宽高
//4。 根据 长宽高 扣费 点卷 或 金币
//
//注意点
//
//1.判断领地是否与其他领地重合
//2.进入领地和离开领地有提示
@CommandHeader("sres", permissionDefault = PermissionDefault.TRUE)
object Command {
    @CommandBody(permission = "sres.help", permissionDefault = PermissionDefault.TRUE)
    val help = subCommand {
        execute<ProxyPlayer> { player, _, _ ->
            for (i in HELP_INFO){
                player.sendMessage(colored(i))
            }
        }
    }
    //选择
    @CommandBody(permission = "sres.select", permissionDefault = PermissionDefault.TRUE)
    val select = subCommand {
        execute<ProxyPlayer> { sender, context,argument ->
            //argument : select _ _ _
            val list = argument.split(" ")
            val player:Player = sender.cast<Player>()
            if (list.size == 2 && (list[1] == "1" || list[1] == "2")){
                if (list[1] == "1"){
                    selectMap[player.name+"-1"] = player.getTargetBlockExact(100)!!.location
                }else{
                    selectMap[player.name+"-2"] = player.getTargetBlockExact(100)!!.location
                }
                sender.sendMessage(SUCCESS_INFO.toString())
            }
            if(list.size==2 && list[1] == "info"){
                try {
                    for (i in SELECT_INFO){
                        var text = ""
                        text = i.replace("%location_1_x%", selectMap[player.name+"-1"]!!.x.toString())
                        text = text.replace("%location_1_y%", selectMap[player.name+"-1"]!!.y.toString())
                        text = text.replace("%location_1_z%", selectMap[player.name+"-1"]!!.z.toString())
                        text = text.replace("%location_2_y%", selectMap[player.name+"-2"]!!.y.toString())
                        text = text.replace("%location_2_x%", selectMap[player.name+"-2"]!!.x.toString())
                        text = text.replace("%location_2_z%", selectMap[player.name+"-2"]!!.z.toString())
                        sender.sendMessage(colored(text))
                    }
                }catch (e:Exception){

                }
            }
        }
    }
//    @CommandBody(permission = "sres", permissionDefault = PermissionDefault.TRUE)
//    val  = subCommand {
//        execute<ProxyPlayer> { player, _, _ ->
//
//        }
//    }
    private fun colored(text:String):String{
        return text.replace("&","§")
    }
}