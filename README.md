# Água da Letícia

Aplicativo Android nativo criado para lembrar a Letícia de beber água ao longo do dia, com interface simples, alarmes locais e notificação em tela cheia.

## Visão geral

O projeto foi desenvolvido em **Java** com **Gradle Kotlin DSL** e usa recursos nativos do Android para:

- ativar e desativar lembretes de hidratação;
- agendar alarmes locais recorrentes;
- exibir notificações de alta prioridade;
- abrir uma tela de alerta com som quando chega o horário;
- restaurar os lembretes após reinicialização do aparelho ou atualização do app.

## Horários dos lembretes

Os lembretes são configurados para os seguintes horários:

- 10:30
- 12:30
- 14:30
- 16:30
- 18:30
- 20:30

## Estrutura principal

- `app/src/main/java/com/luizin/lembreteagua/MainActivity.java` — tela principal e fluxo de permissões.
- `app/src/main/java/com/luizin/lembreteagua/ReminderScheduler.java` — agendamento e cancelamento dos alarmes.
- `app/src/main/java/com/luizin/lembreteagua/AlarmReceiver.java` — recepção do alarme e emissão da notificação.
- `app/src/main/java/com/luizin/lembreteagua/AlarmActivity.java` — alerta em tela cheia com som.
- `app/src/main/java/com/luizin/lembreteagua/BootReceiver.java` — reativa os lembretes após boot ou atualização do pacote.
- `app/src/main/java/com/luizin/lembreteagua/ReminderStateStore.java` — persistência do estado dos lembretes.

## Requisitos

- Android SDK 35
- minSdk 23
- Java 17
- Gradle 8.11.1 no fluxo de CI

## Como executar localmente

1. Abra o projeto no Android Studio.
2. Aguarde a sincronização do Gradle.
3. Execute o módulo `app` em um dispositivo ou emulador Android.
4. Conceda as permissões de notificação e, se necessário, de alarme exato.

## Gerar APK

Pelo terminal, na raiz do projeto:

```bash
./gradlew assembleDebug
```

O APK será gerado em:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Automação

O repositório possui um workflow em `.github/workflows/build-apk.yml` que:

- configura Java 17;
- prepara o Gradle;
- gera o APK de debug;
- publica o artefato `Agua-da-Leticia-APK`.
