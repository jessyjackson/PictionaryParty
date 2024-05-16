import {
    PlusCircle,
} from "lucide-react"

import { Button } from "@/components/ui/button"
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import {
    Table,
    TableBody,
    TableHead,
    TableHeader,
    TableRow,
} from "@/components/ui/table"
import {
    Tabs,
    TabsContent,
} from "@/components/ui/tabs"

import apiClient from "@/data/apiClient";
import { useQuery } from "@tanstack/react-query";
import { useAuthStore } from "@/store/authStore"
import { Word } from "@/apiClient";
import CreateTable from "@/components/WordsTable";


function LandingPage() {

    const auth = useAuthStore();

    const wordsQuery = useQuery({
        queryKey: ["word"],
        queryFn: async() =>{
            const res = await apiClient.wordsApi.apiWordsAllGet()
            return res.data
        },
    });

    return (
            <div>
                <main>
                    <Tabs defaultValue="all">
                        <div className="flex items-center">
                            { auth.user && (<div className="ml-auto flex items-center gap-2">
                                <Button size="sm" className="h-8 gap-1">
                                    <PlusCircle className="h-3.5 w-3.5" />
                                    <span className="sr-only sm:not-sr-only sm:whitespace-nowrap">
                                        Add Product
                                    </span>
                                </Button>
                            </div>)}
                        </div>
                        <TabsContent value="all">
                            <Card x-chunk="dashboard-06-chunk-0">
                                <CardHeader>
                                    <CardTitle>Words</CardTitle>
                                    <CardDescription>
                                        Manage your words.
                                    </CardDescription>
                                </CardHeader>
                                <CardContent>
                                    <Table>
                                        <TableHeader>
                                            <TableRow>
                                            <TableHead className="w-[150px]">Number</TableHead>
                                            <TableHead className="w-[150px]">English</TableHead>
                                            <TableHead className="w-[150px]">Italian</TableHead>
                                            <TableHead className="w-[150px]">Category</TableHead>
                                            </TableRow>
                                        </TableHeader>
                                        <TableBody>
                                            {wordsQuery.data?.map((word: Word) => (
                                                <CreateTable key={word.id} type={word} />
                                            ))}
                                        </TableBody>
                                    </Table>
                                </CardContent>
                            </Card>
                        </TabsContent>
                    </Tabs>
                </main>
            </div>
    )
}

export default LandingPage
