package org.github.ywoxys.kernel.base.account.preferences;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.github.ywoxys.kernel.base.account.permission.group.Rank;

@AllArgsConstructor
@Getter
public enum Preferences {

    CHAT("bate-papo", "Mostrar o bate-papo geral", Rank.MEMBER),
    PRIVATE_MESSAGES("Mensagens privadas", "Ativar ou desativar o recebimento de mensagens privadas", Rank.MEMBER),
    PARTY_INVITE("Convites de Party", "Receber convite de partys", Rank.MEMBER),
    EXTRA_INFORMATION("Informações extras", "Ativar ou desativar Hologramas, Npc's e etc", Rank.MEMBER),
    STATS_VISIBLE("Exibição de status", "Permitir que outras pessoas possam ver suas estatísticas", Rank.MEMBER),
    CONFIRM_TO_LOBBY("Confirmação para voltar ao lobby", "Digitar o comando /lobby duas vezes ao ir ao lobby", Rank.MEMBER),
    FRIENDS_INVITES("Pedidos de amizades", "Receber pedidos de amizades", Rank.MEMBER),
    FLY("Voar no lobby", "ativar o modo de voo nos lobby's", Rank.VIP),
    VANISH_JOIN("Modo Vanish ao Entrar", "", Rank.MODERATOR),
    CHAT_STAFF("Chat Staff", "Mostrar o bate-papo da equipe", Rank.MODERATOR),
    REPORT("Report", "Mostrar os reports", Rank.MODERATOR),
    COMMAND_LOG("Log's de comandos", "Mostrar as log's de comandos executados pelos staff's", Rank.ADMIN);

    private final String name;
    private final String description;
    private final Rank permission;

}
