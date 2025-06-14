import axios from "axios";
import { useEffect, useState } from "react";
import {
  Bar,
  BarChart,
  CartesianGrid,
  Cell,
  Legend,
  Pie,
  PieChart,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";
import "./App.css";

function App() {
  const [vendas, setVendas] = useState([]);
  const [resumo, setResumo] = useState(null);
  const [cabecalhos, setCabecalhos] = useState([]);
  const [dataInicio, setDataInicio] = useState("");
  const [dataFim, setDataFim] = useState("");
  const [codigoEmpresa] = useState("1234567891");

  // Função para buscar os dados do backend com filtro de datas
  const fetchVendas = async () => {
    try {
      const params = {};
      if (dataInicio) params.inicio = dataInicio;
      if (dataFim) params.fim = dataFim;

      const response = await axios.get("/vendas", { params });
      setVendas(response.data);

      const resumoResp = await axios.get("/vendas/resumo", { params });
      console.log("Resumo recebido:", resumoResp.data);
      setResumo(resumoResp.data);
    } catch (error) {
      console.error("Erro ao buscar dados:", error);
      setResumo(null);
      setVendas([]);
    }
  };

  // Função para buscar os dados do cabeçalho
  const fetchCabecalhos = async () => {
    try {
      const response = await axios.get("/cabecalhos");
      setCabecalhos(response.data);
    } catch (error) {
      console.error("Erro ao buscar cabeçalhos:", error);
      setCabecalhos([]);
    }
  };

  // Atualiza dados sempre que datas de filtro mudarem
  useEffect(() => {
    fetchVendas();
  }, [dataInicio, dataFim]);

  // Busca cabeçalhos uma vez no carregamento inicial
  useEffect(() => {
    fetchCabecalhos();
  }, []);

  // Handler do botão filtrar (pode chamar fetchVendas ou deixar vazio porque o useEffect já trata)
  const handleFiltrar = () => {
    fetchVendas();
  };

  const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042", "#9932CC"];

  // Dados para gráfico de pizza: vendas por bandeira
  const bandeirasPieData = resumo
    ? Object.entries(resumo.vendasPorBandeira || {}).map(([bandeira, count]) => ({
        name: bandeira,
        value: count,
      }))
    : [];

  // Dados para gráfico de barras: valor por bandeira
  const bandeirasBarData = resumo
    ? Object.entries(resumo.valorPorBandeira || {}).map(([bandeira, valor]) => ({
        name: bandeira,
        valor: Number(valor),
      }))
    : [];

  // Formatação de valores monetários
  const formatValor = (val) =>
    val?.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });

  return (
    <div className="App">
      {/* Top Bar */}
      <div className="top-bar">
        <span>Relatório de Vendas</span>
        <span>Empresa: {codigoEmpresa}</span>
      </div>

      {/* Exibição dos Cabeçalhos */}
      <div className="summary" style={{ margin: "1rem 0", padding: "1rem", border: "1px solid #ccc", borderRadius: 4 }}>
        <h3>Informações do Cabeçalho</h3>
        {cabecalhos.length === 0 ? (
          <p>Carregando cabeçalhos...</p>
        ) : (
          cabecalhos.map((cabecalho, index) => (
            <div key={index} style={{ marginBottom: "1rem", display: "flex", flexWrap: "wrap", gap: "1.5rem" }}>
              <div><strong>Número Sequencial:</strong> {cabecalho.numeroSequencial}</div>
              <div><strong>Estabelecimento:</strong> {cabecalho.estabelecimento}</div>
              <div><strong>Data Geração Arquivo:</strong> {cabecalho.dataGeracaoArquivo}</div>
              <div><strong>Período Inicial:</strong> {cabecalho.periodoInicial}</div>
              <div><strong>Período Final:</strong> {cabecalho.periodoFinal}</div>
              <div><strong>Empresa Adquirente:</strong> {cabecalho.empresaAdquirente}</div>
              <div><strong>Tipo Extrato:</strong> {cabecalho.tipoExtrato}</div>
              <div><strong>Versão Layout:</strong> {cabecalho.versaoLayout}</div>
              <div><strong>Versão Release:</strong> {cabecalho.versaoRelease}</div>
            </div>
          ))
        )}
      </div>



      {/* Filtros */}
      <div className="filters-wrapper">
        <div className="filters">
          <div>
            <label>Data de Transação Início:</label>
            <input
              type="date"
              value={dataInicio}
              onChange={(e) => setDataInicio(e.target.value)}
            />
          </div>
          <div>
            <label>Data de Transação Fim:</label>
            <input
              type="date"
              value={dataFim}
              onChange={(e) => setDataFim(e.target.value)}
            />
          </div>
          <button onClick={handleFiltrar}>Filtrar</button>
        </div>
      </div>

      {/* Resumo e Gráficos */}
      {resumo && (
        <div className="summary-graphs">
          <div className="summary">
            <h3>Resumo</h3>
            <p>Total de Vendas: {resumo.totalVendas}</p>
            <p>Valor Total: {formatValor(resumo.totalValor)}</p>
            <p>Valor Líquido: {formatValor(resumo.totalLiquido)}</p>
          </div>

          <div
            className="graphs"
            style={{ display: "flex", justifyContent: "space-around" }}
          >
            <div>
              <h4>Vendas por Bandeira</h4>
              <PieChart width={300} height={300}>
                <Pie
                  data={bandeirasPieData}
                  dataKey="value"
                  nameKey="name"
                  outerRadius={100}
                  label
                >
                  {bandeirasPieData.map((_, index) => (
                    <Cell
                      key={`cell-${index}`}
                      fill={COLORS[index % COLORS.length]}
                    />
                  ))}
                </Pie>
                <Tooltip />
              </PieChart>
            </div>

            <div>
              <h4>Valor Total por Bandeira</h4>
              <BarChart width={400} height={300} data={bandeirasBarData}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip formatter={(value) => formatValor(value)} />
                <Legend />
                <Bar dataKey="valor" fill="#8884d8" />
              </BarChart>
            </div>
          </div>
        </div>
      )}

      {/* Tabela */}
      <div style={{ overflowX: "auto" }}>
        <table style={{ borderCollapse: "collapse", width: "100%" }}>
          <thead style={{ backgroundColor: "#f2f2f2" }}>
            <tr>
              <th>Data Transação</th>
              <th>Data Evento</th>
              <th>Hora Evento</th>
              <th>Tipo Evento</th>
              <th>Tipo Transação</th>
              <th>Nº Série Leitor</th>
              <th>Código Transação</th>
              <th>Código Pedido</th>
              <th>Valor Total</th>
              <th>Valor Líquido</th>
              <th>Pagamento</th>
              <th>Plano</th>
              <th>Parcela</th>
              <th>Qtd Parcelas</th>
              <th>Data Prevista Pagamento</th>
              <th>Taxa Parcelamento Comprador</th>
              <th>Tarifa Boleto Comprador</th>
              <th>Valor Original</th>
              <th>Taxa Parcelamento Vendedor</th>
              <th>Taxa Intermediação</th>
              <th>Tarifa Intermediação</th>
              <th>Tarifa Boleto Vendedor</th>
              <th>Repasse Aplicação</th>
              <th>Valor Líquido Transação</th>
              <th>Status Pagamento</th>
              <th>Meio Pagamento</th>
              <th>Bandeira</th>
              <th>Canal Entrada</th>
              <th>Número Leitor</th>
              <th>Número Meio Captura</th>
              <th>Número Lógico</th>
              <th>NSU</th>
              <th>Filler</th>
              <th>Cartão BIN</th>
              <th>Cartão Holder</th>
              <th>Código Autorização</th>
              <th>Código CV</th>
              <th>Reservado Futuros</th>
            </tr>
          </thead>
          <tbody>
            {vendas.map((venda, index) => (
              <tr key={index}>
                <td className="border p-2">{venda.dataTransacao}</td>
                <td className="border p-2">{venda.dataEvento}</td>
                <td className="border p-2">{venda.horaEvento}</td>
                <td className="border p-2">{venda.tipoEvento}</td>
                <td className="border p-2">{venda.tipoTransacao}</td>
                <td className="border p-2">{venda.numeroSerieLeitor}</td>
                <td className="border p-2">{venda.codigoTransacao}</td>
                <td className="border p-2">{venda.codigoPedido}</td>
                <td className="border p-2">
                  {formatValor(venda.valorTotal)}
                </td>
                <td className="border p-2">{formatValor(venda.valorLiquido)}</td>
                <td className="border p-2">{venda.pagamento}</td>
                <td className="border p-2">{venda.plano}</td>
                <td className="border p-2">{venda.parcela}</td>
                <td className="border p-2">{venda.qtdParcelas}</td>
                <td className="border p-2">{venda.dataPrevistaPagamento}</td>
                <td className="border p-2">
                  {formatValor(venda.taxaParcelamentoComprador)}
                </td>
                <td className="border p-2">
                  {formatValor(venda.tarifaBoletoComprador)}
                </td>
                <td className="border p-2">{formatValor(venda.valorOriginal)}</td>
                <td className="border p-2">
                  {formatValor(venda.taxaParcelamentoVendedor)}
                </td>
                <td className="border p-2">{formatValor(venda.taxaIntermediacao)}</td>
                <td className="border p-2">{formatValor(venda.tarifaIntermediacao)}</td>
                <td className="border p-2">{formatValor(venda.tarifaBoletoVendedor)}</td>
                <td className="border p-2">{formatValor(venda.repasseAplicacao)}</td>
                <td className="border p-2">
                  {formatValor(venda.valorLiquidoTransacao)}
                </td>
                <td className="border p-2">{venda.statusPagamento}</td>
                <td className="border p-2">{venda.meioPagamento}</td>
                <td className="border p-2">{venda.bandeira}</td>
                <td className="border p-2">{venda.canalEntrada}</td>
                <td className="border p-2">{venda.numLeitor}</td>
                <td className="border p-2">{venda.numMeioCaptura}</td>
                <td className="border p-2">{venda.numeroLogico}</td>
                <td className="border p-2">{venda.nsu}</td>
                <td className="border p-2">{venda.filler}</td>
                <td className="border p-2">{venda.cartaoBin}</td>
                <td className="border p-2">{venda.cartaoHolder}</td>
                <td className="border p-2">{venda.codigoAutorizacao}</td>
                <td className="border p-2">{venda.codigoCv}</td>
                <td className="border p-2">{venda.reservadoFuturos}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default App;
