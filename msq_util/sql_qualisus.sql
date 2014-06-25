select 
distinct 
cnes.co_ibge,
cnes.nome_mun,
app.codigo_cnes,
apoia.regiao,
app.codigo_apoiador,
apoia.nome,
app.codigo_questionario,
quest.descricao desc_questionario,
app.codigo_pergunta,
perg.descricao desc_pergunta,
app.valor resposta
from msq."Aplicacao" app
inner join msq."Apoiador" apoia on app.codigo_apoiador = apoia.codigo
inner join msq."Questionario" quest on app.codigo_questionario = quest.codigo
inner join msq."Pergunta" perg on app.codigo_pergunta = perg.codigo
left join msq.tb_cnes_ibge cnes on app.codigo_cnes = cnes.co_cnes::text
where co_ibge is not null
order by co_ibge

